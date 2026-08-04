from setuptools import setup, find_packages

with open("README.md", "r", encoding="utf-8") as fh:
    long_description = fh.read()

setup(
    name="document-intelligence",
    version="0.1.0",
    author="haoqing-yan",
    description="An integrated document intelligence system for OCR, table extraction, and chemical formula recognition",
    long_description=long_description,
    long_description_content_type="text/markdown",
    packages=find_packages(),
    classifiers=[
        "Programming Language :: Python :: 3",
        "License :: OSI Approved :: MIT License",
        "Operating System :: OS Independent",
        "Development Status :: 3 - Alpha",
        "Intended Audience :: Developers",
        "Topic :: Scientific/Engineering :: Image Processing",
    ],
    python_requires=">=3.8",
    install_requires=[
        "paddleocr>=2.7.0.3",
        "paddlepaddle>=2.5.0",
        "camelot-py>=0.11.0",
        "img2table>=1.2.0",
        "pillow>=9.0.0",
        "opencv-python>=4.6.0",
        "numpy>=1.21.0",
        "pandas>=1.3.0",
        "rdkit>=2023.03.1",
        "pdfplumber>=0.9.0",
        "PyPDF2>=3.0.0",
        "pyyaml>=6.0",
        "tqdm>=4.64.0",
        "requests>=2.28.0",
    ],
)
