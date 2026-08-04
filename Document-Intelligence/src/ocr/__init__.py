"""OCR Module"""

from src.ocr.paddle_ocr import PaddleOCREngine
from src.ocr.text_processor import TextProcessor

__all__ = ["PaddleOCREngine", "TextProcessor"]
