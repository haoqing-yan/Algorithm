"""File handling utilities"""

import logging
import os
from pathlib import Path
from typing import List, Optional

logger = logging.getLogger(__name__)


class FileHandler:
    """Handle file operations"""

    @staticmethod
    def check_file_exists(file_path: str) -> bool:
        """
        Check if file exists

        Args:
            file_path: Path to file

        Returns:
            True if file exists
        """
        return os.path.isfile(file_path)

    @staticmethod
    def get_file_extension(file_path: str) -> str:
        """
        Get file extension

        Args:
            file_path: Path to file

        Returns:
            File extension without dot
        """
        return Path(file_path).suffix.lstrip('.')

    @staticmethod
    def is_image(file_path: str) -> bool:
        """
        Check if file is an image

        Args:
            file_path: Path to file

        Returns:
            True if file is an image
        """
        image_extensions = {'jpg', 'jpeg', 'png', 'bmp', 'gif', 'tiff', 'webp'}
        ext = FileHandler.get_file_extension(file_path).lower()
        return ext in image_extensions

    @staticmethod
    def is_pdf(file_path: str) -> bool:
        """
        Check if file is a PDF

        Args:
            file_path: Path to file

        Returns:
            True if file is a PDF
        """
        return FileHandler.get_file_extension(file_path).lower() == 'pdf'

    @staticmethod
    def create_directory(directory_path: str) -> None:
        """
        Create directory if it doesn't exist

        Args:
            directory_path: Path to directory
        """
        Path(directory_path).mkdir(parents=True, exist_ok=True)
        logger.info(f"Created directory: {directory_path}")

    @staticmethod
    def list_files(directory_path: str, extension: Optional[str] = None) -> List[str]:
        """
        List files in directory

        Args:
            directory_path: Path to directory
            extension: Filter by extension (without dot)

        Returns:
            List of file paths
        """
        if not os.path.isdir(directory_path):
            logger.warning(f"Directory not found: {directory_path}")
            return []
        
        files = []
        for file in os.listdir(directory_path):
            file_path = os.path.join(directory_path, file)
            if os.path.isfile(file_path):
                if extension is None or FileHandler.get_file_extension(file_path).lower() == extension.lower():
                    files.append(file_path)
        
        return files

    @staticmethod
    def get_file_size(file_path: str) -> int:
        """
        Get file size in bytes

        Args:
            file_path: Path to file

        Returns:
            File size in bytes
        """
        if os.path.isfile(file_path):
            return os.path.getsize(file_path)
        return 0
