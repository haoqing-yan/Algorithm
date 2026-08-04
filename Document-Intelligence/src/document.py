"""Main Document Intelligence class"""

import logging
from typing import Dict, List, Optional, Union
from pathlib import Path

from src.ocr.paddle_ocr import PaddleOCREngine
from src.ocr.text_processor import TextProcessor
from src.table.table_extractor import TableExtractor
from src.table.table_parser import TableParser
from src.formula.formula_detector import FormulaDetector
from src.formula.formula_validator import FormulaValidator
from src.utils.image_processor import ImageProcessor
from src.utils.file_handler import FileHandler
from src.utils.logger import setup_logger


class DocumentIntelligence:
    """
    Main class for document intelligence
    Integrates OCR, table extraction, and formula recognition
    """

    def __init__(
        self,
        use_gpu: bool = True,
        ocr_langs: Optional[List[str]] = None,
        table_method: str = "camelot",
        log_level: str = "INFO",
        log_file: Optional[str] = None,
    ):
        """
        Initialize Document Intelligence system

        Args:
            use_gpu: Whether to use GPU for OCR
            ocr_langs: Languages for OCR (default: ['ch', 'en'])
            table_method: Method for table extraction ('camelot' or 'img2table')
            log_level: Logging level
            log_file: Path to log file (optional)
        """
        self.logger = setup_logger(
            name="DocumentIntelligence",
            log_level=log_level,
            log_file=log_file,
        )

        if ocr_langs is None:
            ocr_langs = ["ch", "en"]

        # Initialize components
        self.logger.info("Initializing Document Intelligence System")
        self.ocr_engine = PaddleOCREngine(use_gpu=use_gpu, lang=ocr_langs)
        self.text_processor = TextProcessor()
        self.table_extractor = TableExtractor(method=table_method)
        self.table_parser = TableParser()
        self.formula_detector = FormulaDetector()
        self.formula_validator = FormulaValidator()
        self.image_processor = ImageProcessor()
        self.file_handler = FileHandler()

    def process(
        self,
        input_path: str,
        enhance: bool = True,
        extract_formulas: bool = True,
    ) -> Dict:
        """
        Process document (image or PDF)

        Args:
            input_path: Path to image or PDF file
            enhance: Whether to enhance image quality
            extract_formulas: Whether to extract chemical formulas

        Returns:
            Dictionary with OCR results, tables, and formulas
        """
        if not self.file_handler.check_file_exists(input_path):
            raise FileNotFoundError(f"File not found: {input_path}")

        self.logger.info(f"Processing document: {input_path}")
        result = {
            "file": input_path,
            "text": [],
            "full_text": "",
            "tables": [],
            "formulas": [],
            "confidence": [],
        }

        # Process based on file type
        if self.file_handler.is_image(input_path):
            result = self._process_image(input_path, enhance, extract_formulas)
        elif self.file_handler.is_pdf(input_path):
            result = self._process_pdf(input_path, extract_formulas)
        else:
            raise ValueError(f"Unsupported file type: {input_path}")

        self.logger.info("Document processing completed")
        return result

    def _process_image(
        self, image_path: str, enhance: bool = True, extract_formulas: bool = True
    ) -> Dict:
        """
        Process image file
        """
        result = {
            "file": image_path,
            "text": [],
            "full_text": "",
            "tables": [],
            "formulas": [],
            "confidence": [],
        }

        try:
            # Enhance image if requested
            if enhance:
                self.logger.info("Enhancing image...")
                enhanced_image = self.image_processor.enhance_contrast(image_path)
                # Save temporarily for OCR
                temp_path = "temp_enhanced.jpg"
                self.image_processor.save_image(enhanced_image, temp_path)
                process_path = temp_path
            else:
                process_path = image_path

            # OCR
            self.logger.info("Running OCR...")
            ocr_result = self.ocr_engine.recognize(process_path)
            result["text"] = ocr_result["text"]
            result["confidence"] = ocr_result["confidence"]
            result["full_text"] = " ".join(ocr_result["text"])

            # Extract tables
            self.logger.info("Extracting tables...")
            try:
                tables = self.table_extractor.extract_from_image(process_path)
                result["tables"] = tables
            except Exception as e:
                self.logger.warning(f"Table extraction failed: {e}")

            # Extract formulas
            if extract_formulas:
                self.logger.info("Extracting chemical formulas...")
                formulas = self.formula_detector.detect_in_text(result["full_text"])
                result["formulas"] = formulas

            # Cleanup
            if enhance and Path(temp_path).exists():
                Path(temp_path).unlink()

        except Exception as e:
            self.logger.error(f"Error processing image: {e}")
            raise

        return result

    def _process_pdf(self, pdf_path: str, extract_formulas: bool = True) -> Dict:
        """
        Process PDF file
        """
        result = {
            "file": pdf_path,
            "text": [],
            "full_text": "",
            "tables": [],
            "formulas": [],
            "confidence": [],
        }

        try:
            # Extract tables
            self.logger.info("Extracting tables from PDF...")
            tables = self.table_extractor.extract_from_pdf(pdf_path)
            result["tables"] = tables

        except Exception as e:
            self.logger.error(f"Error processing PDF: {e}")
            raise

        return result

    def batch_process(
        self, directory_path: str, extension: str = "jpg", **kwargs
    ) -> List[Dict]:
        """
        Process multiple files in a directory

        Args:
            directory_path: Path to directory
            extension: File extension to process
            **kwargs: Additional arguments for process method

        Returns:
            List of results
        """
        files = self.file_handler.list_files(directory_path, extension)
        self.logger.info(f"Found {len(files)} files to process")

        results = []
        for i, file_path in enumerate(files, 1):
            self.logger.info(f"Processing file {i}/{len(files)}: {file_path}")
            try:
                result = self.process(file_path, **kwargs)
                results.append(result)
            except Exception as e:
                self.logger.error(f"Error processing {file_path}: {e}")
                results.append({"file": file_path, "error": str(e)})

        return results
