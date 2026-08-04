"""PaddleOCR Engine for text recognition"""

import logging
from typing import List, Dict, Tuple, Optional
import cv2
import numpy as np

try:
    from paddleocr import PaddleOCR
except ImportError:
    raise ImportError("Please install paddleocr: pip install paddleocr")

logger = logging.getLogger(__name__)


class PaddleOCREngine:
    """PaddleOCR wrapper for text detection and recognition"""

    def __init__(
        self,
        use_gpu: bool = True,
        use_angle_cls: bool = True,
        lang: Optional[List[str]] = None,
    ):
        """
        Initialize PaddleOCR engine

        Args:
            use_gpu: Whether to use GPU
            use_angle_cls: Whether to use angle classification
            lang: Languages to recognize, default ['ch', 'en']
        """
        if lang is None:
            lang = ["ch", "en"]

        logger.info(f"Initializing PaddleOCR with languages: {lang}")
        self.ocr = PaddleOCR(use_gpu=use_gpu, use_angle_cls=use_angle_cls, lang=lang)
        self.use_gpu = use_gpu
        self.lang = lang

    def recognize(
        self, image_path: str, return_format: str = "dict"
    ) -> Dict | List:
        """
        Recognize text from image

        Args:
            image_path: Path to image file
            return_format: Format of return value ('dict' or 'list')

        Returns:
            Recognized text and confidence scores
        """
        try:
            logger.info(f"Processing image: {image_path}")
            result = self.ocr.ocr(image_path, cls=True)
            
            if return_format == "dict":
                return self._format_as_dict(result)
            else:
                return result
        except Exception as e:
            logger.error(f"Error during OCR recognition: {e}")
            raise

    def recognize_batch(
        self, image_paths: List[str], return_format: str = "dict"
    ) -> List[Dict | List]:
        """
        Recognize text from multiple images

        Args:
            image_paths: List of paths to image files
            return_format: Format of return value ('dict' or 'list')

        Returns:
            List of recognized results
        """
        results = []
        for path in image_paths:
            result = self.recognize(path, return_format)
            results.append(result)
        return results

    def _format_as_dict(self, ocr_result: List) -> Dict:
        """
        Format OCR result as dictionary

        Args:
            ocr_result: Raw OCR result from PaddleOCR

        Returns:
            Formatted dictionary with text and confidence
        """
        formatted = {"text": [], "confidence": [], "boxes": []}

        for line in ocr_result:
            if line:
                for item in line:
                    box, (text, conf) = item
                    formatted["text"].append(text)
                    formatted["confidence"].append(conf)
                    formatted["boxes"].append(box)

        return formatted

    def get_full_text(self, image_path: str, separator: str = "\n") -> str:
        """
        Extract full text from image

        Args:
            image_path: Path to image file
            separator: Separator between lines

        Returns:
            Concatenated text
        """
        result = self.recognize(image_path)
        text = separator.join(result["text"])
        return text
