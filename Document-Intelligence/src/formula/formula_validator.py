"""Validate and process chemical formulas"""

import logging
from typing import Dict, Optional, Tuple

try:
    from rdkit import Chem
    from rdkit.Chem import Descriptors, Crippen
    HAS_RDKIT = True
except ImportError:
    HAS_RDKIT = False
    logging.warning("RDKit not installed. Some features will be unavailable.")

logger = logging.getLogger(__name__)


class FormulaValidator:
    """Validate and convert chemical formulas"""

    def __init__(self):
        """Initialize formula validator"""
        self.has_rdkit = HAS_RDKIT
        if not HAS_RDKIT:
            logger.warning("RDKit not available. Use basic validation only.")

    def validate_formula(self, formula: str) -> Tuple[bool, str]:
        """
        Validate chemical formula

        Args:
            formula: Chemical formula string

        Returns:
            Tuple of (is_valid, message)
        """
        if not formula:
            return False, "Empty formula"
        
        # Basic checks
        if not formula[0].isupper():
            return False, "Formula must start with uppercase letter"
        
        if not self.has_rdkit:
            return self._basic_validation(formula)
        
        return self._rdkit_validation(formula)

    def smiles_to_formula(self, smiles: str) -> Optional[str]:
        """
        Convert SMILES notation to molecular formula

        Args:
            smiles: SMILES notation string

        Returns:
            Molecular formula or None if invalid
        """
        if not self.has_rdkit:
            logger.warning("RDKit required for SMILES conversion")
            return None
        
        try:
            mol = Chem.MolFromSmiles(smiles)
            if mol is None:
                return None
            
            formula = Chem.rdMolDescriptors.CalcMolFormula(mol)
            return formula
        except Exception as e:
            logger.error(f"Error converting SMILES to formula: {e}")
            return None

    def formula_to_smiles(self, formula: str) -> Optional[str]:
        """
        Try to convert molecular formula to SMILES
        Note: This is limited as multiple SMILES can represent the same formula

        Args:
            formula: Molecular formula string

        Returns:
            SMILES string or None if conversion failed
        """
        if not self.has_rdkit:
            logger.warning("RDKit required for formula to SMILES conversion")
            return None
        
        try:
            # Use SMARTS to attempt conversion
            mol = Chem.MolFromSmarts(formula)
            if mol is not None:
                return Chem.MolToSmiles(mol)
            return None
        except Exception as e:
            logger.error(f"Error converting formula to SMILES: {e}")
            return None

    def get_molecular_weight(self, formula: str) -> Optional[float]:
        """
        Calculate molecular weight

        Args:
            formula: Molecular formula string

        Returns:
            Molecular weight or None if calculation failed
        """
        if not self.has_rdkit:
            return None
        
        try:
            mol = Chem.MolFromSmiles(formula)
            if mol is None:
                return None
            
            weight = Descriptors.MolWt(mol)
            return round(weight, 2)
        except Exception as e:
            logger.error(f"Error calculating molecular weight: {e}")
            return None

    def _basic_validation(self, formula: str) -> Tuple[bool, str]:
        """
        Basic formula validation without RDKit
        """
        # Check for invalid characters
        valid_chars = set("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789()[]")
        formula_chars = set(formula)
        
        if not formula_chars.issubset(valid_chars):
            invalid = formula_chars - valid_chars
            return False, f"Invalid characters: {invalid}"
        
        # Check for balanced parentheses
        if formula.count("(") != formula.count(")"):
            return False, "Unbalanced parentheses"
        
        if formula.count("[") != formula.count("]"):
            return False, "Unbalanced brackets"
        
        return True, "Valid formula (basic validation)"

    def _rdkit_validation(self, formula: str) -> Tuple[bool, str]:
        """
        Validate formula using RDKit
        """
        try:
            mol = Chem.MolFromSmiles(formula)
            if mol is None:
                return False, "Invalid SMILES/formula"
            
            return True, "Valid formula"
        except Exception as e:
            return False, f"Validation error: {str(e)}"
