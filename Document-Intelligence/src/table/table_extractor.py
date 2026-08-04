"""Table extraction from images and PDFs"""

import logging
from typing import List, Dict, Optional, Tuple
import cv2
import numpy as np

try:
    import camelot
except ImportError:
    raise ImportError("Please install camelot-py: pip install camelot-py")

try:
    from img2table.document import Document as Img2TableDocument
except ImportError:
    Img2TableDocument = None

logger = logging.getLogger(__name__)


class TableExtractor:
    """Extract tables from images and PDFs"""

    def __init__(
        self,
        method: str = "camelot",  # 'camelot' or 'img2table'
        flavor: str = "lattice",  # For camelot: 'lattice' or 'stream'
    ):
        """
        Initialize table extractor

        Args:
            method: Extraction method ('camelot' or 'img2table')
            flavor: For camelot, 'lattice' for tables with lines, 'stream' for otherwise
        """
        self.method = method
        self.flavor = flavor
        logger.info(f"Initializing TableExtractor with method: {method}")

    def extract_from_pdf(
        self, pdf_path: str, pages: Optional[List[int]] = None
    ) -> List[Dict]:
        """
        Extract tables from PDF

        Args:
            pdf_path: Path to PDF file
            pages: List of page numbers to extract from (1-indexed)

        Returns:
            List of extracted tables
        """
        if self.method == "camelot":
            return self._extract_pdf_camelot(pdf_path, pages)
        else:
            return self._extract_pdf_img2table(pdf_path)

    def extract_from_image(self, image_path: str) -> List[Dict]:
        """
        Extract tables from image

        Args:
            image_path: Path to image file

        Returns:
            List of extracted tables
        """
        if self.method == "img2table":
            return self._extract_image_img2table(image_path)
        else:
            logger.warning("Camelot doesn't support direct image extraction")
            return self._extract_image_opencv(image_path)

    def _extract_pdf_camelot(
        self, pdf_path: str, pages: Optional[List[int]] = None
    ) -> List[Dict]:
        """
        Extract tables using Camelot from PDF
        """
        try:
            logger.info(f"Extracting tables from PDF using Camelot: {pdf_path}")
            
            if pages:
                pages_str = ",".join(map(str, pages))
                tables = camelot.read_pdf(pdf_path, pages=pages_str, flavor=self.flavor)
            else:
                tables = camelot.read_pdf(pdf_path, flavor=self.flavor)

            results = []
            for table in tables:
                results.append({
                    "data": table.df,
                    "html": table.as_html(),
                    "csv": table.as_csv(),
                    "confidences": table.accuracy,
                    "page": table.page_num,
                })
            
            logger.info(f"Extracted {len(results)} tables")
            return results
        except Exception as e:
            logger.error(f"Error extracting tables from PDF: {e}")
            raise

    def _extract_pdf_img2table(self, pdf_path: str) -> List[Dict]:
        """
        Extract tables using img2table from PDF
        """
        if Img2TableDocument is None:
            raise ImportError("img2table is not installed")
        
        try:
            logger.info(f"Extracting tables using img2table: {pdf_path}")
            doc = Img2TableDocument(pdf_path)
            tables = doc.extract_tables()
            
            results = []
            for table in tables:
                results.append({
                    "data": table.df,
                    "cells": table.cells,
                })
            
            return results
        except Exception as e:
            logger.error(f"Error extracting tables: {e}")
            raise

    def _extract_image_img2table(self, image_path: str) -> List[Dict]:
        """
        Extract tables from image using img2table
        """
        if Img2TableDocument is None:
            raise ImportError("img2table is not installed")
        
        try:
            logger.info(f"Extracting tables from image using img2table: {image_path}")
            doc = Img2TableDocument(image_path)
            tables = doc.extract_tables()
            
            results = []
            for table in tables:
                results.append({
                    "data": table.df,
                    "cells": table.cells,
                })
            
            return results
        except Exception as e:
            logger.error(f"Error extracting tables: {e}")
            raise

    def _extract_image_opencv(self, image_path: str) -> List[Dict]:
        """
        Extract table structure from image using OpenCV
        """
        try:
            logger.info(f"Extracting table structure from image using OpenCV: {image_path}")
            
            # Read image
            image = cv2.imread(image_path)
            gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
            
            # Detect table structure
            _, binary = cv2.threshold(gray, 150, 255, cv2.THRESH_BINARY)
            
            # Find contours (table cells)
            contours, _ = cv2.findContours(binary, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
            
            # Extract cell information
            cells = []
            for contour in contours:
                x, y, w, h = cv2.boundingRect(contour)
                if w > 10 and h > 10:  # Filter small noise
                    cells.append({"x": x, "y": y, "width": w, "height": h})
            
            return [{
                "cells": cells,
                "image_path": image_path,
                "method": "opencv",
            }]
        except Exception as e:
            logger.error(f"Error extracting table structure: {e}")
            raise
