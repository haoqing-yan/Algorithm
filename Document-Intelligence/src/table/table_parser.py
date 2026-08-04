"""Parse and process extracted tables"""

import logging
from typing import List, Dict, Any
import pandas as pd
import json

logger = logging.getLogger(__name__)


class TableParser:
    """Parse and convert extracted tables to different formats"""

    @staticmethod
    def to_dataframe(table_data: Dict) -> pd.DataFrame:
        """
        Convert table data to pandas DataFrame

        Args:
            table_data: Extracted table data

        Returns:
            pandas DataFrame
        """
        if "data" in table_data and isinstance(table_data["data"], pd.DataFrame):
            return table_data["data"]
        elif "cells" in table_data:
            return TableParser._cells_to_dataframe(table_data["cells"])
        else:
            raise ValueError("Cannot convert to DataFrame")

    @staticmethod
    def to_csv(table_data: Dict, output_path: str) -> None:
        """
        Save table as CSV

        Args:
            table_data: Extracted table data
            output_path: Path to save CSV file
        """
        if "csv" in table_data:
            with open(output_path, "w", encoding="utf-8") as f:
                f.write(table_data["csv"])
        else:
            df = TableParser.to_dataframe(table_data)
            df.to_csv(output_path, index=False, encoding="utf-8")
        logger.info(f"Saved table to CSV: {output_path}")

    @staticmethod
    def to_json(table_data: Dict, output_path: str) -> None:
        """
        Save table as JSON

        Args:
            table_data: Extracted table data
            output_path: Path to save JSON file
        """
        if "data" in table_data:
            df = table_data["data"]
            df.to_json(output_path, orient="records", force_ascii=False, indent=2)
        else:
            with open(output_path, "w", encoding="utf-8") as f:
                json.dump(table_data, f, ensure_ascii=False, indent=2)
        logger.info(f"Saved table to JSON: {output_path}")

    @staticmethod
    def to_html(table_data: Dict, output_path: str) -> None:
        """
        Save table as HTML

        Args:
            table_data: Extracted table data
            output_path: Path to save HTML file
        """
        if "html" in table_data:
            with open(output_path, "w", encoding="utf-8") as f:
                f.write(table_data["html"])
        else:
            df = TableParser.to_dataframe(table_data)
            html = df.to_html(index=False)
            with open(output_path, "w", encoding="utf-8") as f:
                f.write(html)
        logger.info(f"Saved table to HTML: {output_path}")

    @staticmethod
    def _cells_to_dataframe(cells: List[Dict]) -> pd.DataFrame:
        """
        Convert cell data to DataFrame
        
        Args:
            cells: List of cell dictionaries
            
        Returns:
            pandas DataFrame
        """
        if not cells:
            return pd.DataFrame()
        
        # Group cells by row (y coordinate)
        rows = {}
        for cell in cells:
            y = cell.get("y", 0)
            if y not in rows:
                rows[y] = []
            rows[y].append(cell)
        
        # Convert to DataFrame
        data = []
        for y in sorted(rows.keys()):
            row_cells = sorted(rows[y], key=lambda x: x.get("x", 0))
            row = [cell.get("content", "") for cell in row_cells]
            data.append(row)
        
        return pd.DataFrame(data)

    @staticmethod
    def clean_table(table_data: Dict) -> Dict:
        """
        Clean table data
        
        Args:
            table_data: Extracted table data
            
        Returns:
            Cleaned table data
        """
        if "data" in table_data:
            df = table_data["data"]
            # Remove empty rows and columns
            df = df.dropna(how="all")
            df = df.loc[:, ~df.columns.str.contains("^Unnamed")]
            table_data["data"] = df
        
        return table_data
