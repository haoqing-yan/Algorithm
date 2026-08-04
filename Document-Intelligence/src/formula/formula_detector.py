"""Detect chemical formulas in text and images"""

import re
import logging
from typing import List, Dict, Optional, Tuple

logger = logging.getLogger(__name__)


class FormulaDetector:
    """Detect and extract chemical formulas from text"""

    # Common chemical formula patterns
    FORMULA_PATTERNS = [
        # Simple formulas like H2O, CO2
        r"[A-Z][a-z]?(?:\d+)?(?:[A-Z][a-z]?(?:\d+)?)*",
        # Complex formulas with parentheses like Ca(OH)2
        r"[A-Z][a-z]?\((?:[A-Z][a-z]?\d*)+\)\d*",
    ]

    def __init__(self):
        """Initialize formula detector"""
        self.patterns = [re.compile(p) for p in self.FORMULA_PATTERNS]
        logger.info("FormulaDetector initialized")

    def detect_in_text(self, text: str) -> List[Dict]:
        """
        Detect chemical formulas in text

        Args:
            text: Input text

        Returns:
            List of detected formulas with positions
        """
        formulas = []
        
        # Pattern 1: Simple formulas (H2O, CO2, NaCl, etc.)
        simple_pattern = r"\b([A-Z][a-z]?(?:\d+)?(?:[A-Z][a-z]?(?:\d+)?)*(?:\([A-Za-z0-9]+\)\d+)?)\b"
        matches = re.finditer(simple_pattern, text)
        
        for match in matches:
            formula = match.group(1)
            if self._is_valid_formula(formula):
                formulas.append({
                    "formula": formula,
                    "start": match.start(),
                    "end": match.end(),
                    "confidence": 0.8,
                })
        
        logger.info(f"Detected {len(formulas)} formulas in text")
        return formulas

    def detect_in_ocr_result(self, ocr_result: List[str]) -> List[Dict]:
        """
        Detect chemical formulas in OCR results

        Args:
            ocr_result: List of OCR text lines

        Returns:
            List of detected formulas
        """
        all_formulas = []
        
        for line_idx, line in enumerate(ocr_result):
            formulas = self.detect_in_text(line)
            for formula in formulas:
                formula["line"] = line_idx
                all_formulas.append(formula)
        
        return all_formulas

    def extract_smiles(self, text: str) -> List[Dict]:
        """
        Extract SMILES notation from text

        Args:
            text: Input text

        Returns:
            List of detected SMILES strings
        """
        # SMILES pattern (simplified)
        smiles_pattern = r"([C[()\]\-=#@~:0-9]+)"
        matches = re.finditer(smiles_pattern, text)
        
        smiles_list = []
        for match in matches:
            smiles = match.group(1)
            if len(smiles) > 3 and self._is_likely_smiles(smiles):
                smiles_list.append({
                    "smiles": smiles,
                    "start": match.start(),
                    "end": match.end(),
                })
        
        return smiles_list

    def _is_valid_formula(self, formula: str) -> bool:
        """
        Check if string is a valid chemical formula

        Args:
            formula: Formula string

        Returns:
            Whether the formula is valid
        """
        # Must start with uppercase letter
        if not formula or not formula[0].isupper():
            return False
        
        # Should not be just a single letter (unless it's an element)
        if len(formula) == 1:
            return True
        
        # Should contain at least one letter
        if not any(c.isalpha() for c in formula):
            return False
        
        # Common elements
        common_elements = {
            "H", "C", "N", "O", "P", "S", "Cl", "Br", "F", "I",
            "Na", "K", "Ca", "Mg", "Fe", "Cu", "Zn", "Al", "Si",
            "B", "As", "Se", "Hg", "Pb", "Ni", "Co", "Mn", "Cr"
        }
        
        # Extract first element
        first_elem = formula[0]
        if len(formula) > 1 and formula[1].islower():
            first_elem = formula[:2]
        
        return first_elem in common_elements

    def _is_likely_smiles(self, text: str) -> bool:
        """
        Check if text is likely SMILES notation

        Args:
            text: Text to check

        Returns:
            Whether text looks like SMILES
        """
        # Should contain organic chemistry characters
        smiles_chars = set("CNOPSFClBrI()[]=#@~-:0123456789\\")
        text_chars = set(text)
        
        return len(text_chars - smiles_chars) < len(text_chars) * 0.3
