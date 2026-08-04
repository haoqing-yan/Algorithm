"""Image processing utilities"""

import logging
from typing import Tuple, Optional
import cv2
import numpy as np
from PIL import Image

logger = logging.getLogger(__name__)


class ImageProcessor:
    """Process and enhance images"""

    @staticmethod
    def resize(image_path: str, max_width: int = 2560, max_height: int = 1920) -> np.ndarray:
        """
        Resize image to fit within max dimensions

        Args:
            image_path: Path to image
            max_width: Maximum width
            max_height: Maximum height

        Returns:
            Resized image array
        """
        try:
            image = cv2.imread(image_path)
            height, width = image.shape[:2]
            
            if width > max_width or height > max_height:
                scale = min(max_width / width, max_height / height)
                new_width = int(width * scale)
                new_height = int(height * scale)
                image = cv2.resize(image, (new_width, new_height))
                logger.info(f"Resized image from {width}x{height} to {new_width}x{new_height}")
            
            return image
        except Exception as e:
            logger.error(f"Error resizing image: {e}")
            raise

    @staticmethod
    def enhance_contrast(image_path: str) -> np.ndarray:
        """
        Enhance image contrast for better OCR

        Args:
            image_path: Path to image

        Returns:
            Enhanced image array
        """
        try:
            image = cv2.imread(image_path)
            lab = cv2.cvtColor(image, cv2.COLOR_BGR2LAB)
            l, a, b = cv2.split(lab)
            
            # Apply CLAHE (Contrast Limited Adaptive Histogram Equalization)
            clahe = cv2.createCLAHE(clipLimit=3.0, tileGridSize=(8, 8))
            l = clahe.apply(l)
            
            enhanced = cv2.merge([l, a, b])
            enhanced = cv2.cvtColor(enhanced, cv2.COLOR_LAB2BGR)
            
            logger.info("Enhanced image contrast")
            return enhanced
        except Exception as e:
            logger.error(f"Error enhancing contrast: {e}")
            raise

    @staticmethod
    def deskew(image_path: str) -> np.ndarray:
        """
        Deskew image

        Args:
            image_path: Path to image

        Returns:
            Deskewed image array
        """
        try:
            image = cv2.imread(image_path)
            gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
            
            # Get Hough lines
            edges = cv2.Canny(gray, 50, 150)
            lines = cv2.HoughLinesP(edges, 1, np.pi / 180, 50, minLineLength=50, maxLineGap=10)
            
            if lines is None:
                return image
            
            # Calculate rotation angle
            angles = []
            for line in lines:
                x1, y1, x2, y2 = line[0]
                angle = np.arctan2(y2 - y1, x2 - x1)
                angles.append(angle)
            
            median_angle = np.median(angles)
            
            # Rotate image
            height, width = image.shape[:2]
            center = (width // 2, height // 2)
            rotation_matrix = cv2.getRotationMatrix2D(center, np.degrees(median_angle), 1.0)
            rotated = cv2.warpAffine(image, rotation_matrix, (width, height))
            
            logger.info(f"Deskewed image by {np.degrees(median_angle):.2f} degrees")
            return rotated
        except Exception as e:
            logger.error(f"Error deskewing image: {e}")
            raise

    @staticmethod
    def denoise(image_path: str) -> np.ndarray:
        """
        Denoise image

        Args:
            image_path: Path to image

        Returns:
            Denoised image array
        """
        try:
            image = cv2.imread(image_path)
            denoised = cv2.fastNlMeansDenoisingColored(image, None, h=10, hForColorComponents=10, templateWindowSize=7, searchWindowSize=21)
            
            logger.info("Denoised image")
            return denoised
        except Exception as e:
            logger.error(f"Error denoising image: {e}")
            raise

    @staticmethod
    def save_image(image: np.ndarray, output_path: str, quality: int = 95) -> None:
        """
        Save image to file

        Args:
            image: Image array
            output_path: Path to save image
            quality: JPEG quality (1-100)
        """
        try:
            if output_path.lower().endswith('.jpg') or output_path.lower().endswith('.jpeg'):
                cv2.imwrite(output_path, image, [cv2.IMWRITE_JPEG_QUALITY, quality])
            else:
                cv2.imwrite(output_path, image)
            logger.info(f"Saved image to {output_path}")
        except Exception as e:
            logger.error(f"Error saving image: {e}")
            raise
