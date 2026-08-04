"""Text processing utilities"""

import re
import logging
from typing import List, Dict, Tuple

logger = logging.getLogger(__name__)


class TextProcessor:
    """Process and clean OCR results"""

    @staticmethod
    def clean_text(text: str) -> str:
        """
        Clean OCR text

        Args:
            text: Raw text from OCR

        Returns:
            Cleaned text
        """
        # Remove extra whitespace
        text = re.sub(r"\s+", " ", text).strip()
        # Remove common OCR artifacts
        text = text.replace("\x00", "")
        return text

    @staticmethod
    def extract_lines(result: List) -> List[str]:
        """
        Extract lines from OCR result

        Args:
            result: OCR result

        Returns:
            List of text lines
        """
        lines = []
        for line in result:
            if line:
                text = "".join([item[1][0] for item in line])
                lines.append(text)
        return lines

    @staticmethod
    def extract_high_confidence(
        result: List, threshold: float = 0.5
    ) -> List[str]:
        """
        Extract text with confidence above threshold

        Args:
            result: OCR result
            threshold: Confidence threshold

        Returns:
            List of high-confidence texts
        """
        texts = []
        for line in result:
            if line:
                for item in line:
                    text, confidence = item[1]
                    if confidence >= threshold:
                        texts.append(text)
        return texts

    @staticmethod
    def merge_lines(lines: List[str], separator: str = " ") -> str:
        """
        Merge lines into single text

        Args:
            lines: List of text lines
            separator: Separator between lines

        Returns:
            Merged text
        """
        return separator.join(lines)
