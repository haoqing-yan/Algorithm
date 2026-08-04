# Document Intelligence System

一个整合 OCR、表格提取、化学分子式识别的文档智能识别系统。

## 🎯 功能特性

- **文字识别 (OCR)**: 使用 PaddleOCR 支持 100+ 语言
- **表格提取**: 图片和 PDF 表格自动识别与解析
- **分子式识别**: 化学结构式识别和标准化处理
- **智能分割**: 自动检测文档中的文本、表格、分子式区域
- **结构化输出**: JSON/CSV 格式导出

## 📦 项目结构

```
Document-Intelligence/
├── README.md
├── requirements.txt
├── setup.py
├── config/
│   └── config.yaml
├── src/
│   ├── __init__.py
│   ├── ocr/
│   │   ├── __init__.py
│   │   ├── paddle_ocr.py
│   │   └── text_processor.py
│   ├── table/
│   │   ├── __init__.py
│   │   ├── table_extractor.py
│   │   └── table_parser.py
│   ├── formula/
│   │   ├── __init__.py
│   │   ├── formula_detector.py
│   │   └── formula_validator.py
│   ├── utils/
│   │   ├── __init__.py
│   │   ├── image_processor.py
│   │   ├── file_handler.py
│   │   └── logger.py
│   └── document.py
├── examples/
│   ├── basic_ocr.py
│   ├── table_extraction.py
│   ├── formula_recognition.py
│   └── full_pipeline.py
├── tests/
│   ├── __init__.py
│   ├── test_ocr.py
│   ├── test_table.py
│   └── test_formula.py
└── data/
    ├── samples/
    └── results/
```

## 🚀 快速开始

### 安装依赖

```bash
pip install -r requirements.txt
```

### 基础使用

```python
from src.document import DocumentIntelligence

# 初始化
doc_intel = DocumentIntelligence()

# 处理图片
result = doc_intel.process("path/to/image.jpg")

# 获取结果
print(result['text'])      # 识别的文本
print(result['tables'])    # 提取的表格
print(result['formulas'])  # 识别的分子式
```

## 📋 详细功能

### 1. OCR 文字识别
- 支持中文、英文、多语言
- 自动文本方向检测
- 保留原始排版信息

### 2. 表格提取
- 支持图片表格和 PDF 表格
- 自动边界检测
- 导出为 CSV、JSON、DataFrame

### 3. 化学分子式识别
- 图片中的分子式识别
- SMILES 格式转换
- 分子式验证和标准化

## 📖 使用示例

详见 `examples/` 目录

## 🔧 配置

编辑 `config/config.yaml` 自定义参数

## 📝 License

MIT

## 👨‍💻 贡献

欢迎提交 Issue 和 Pull Request！

## 📞 联系方式

如有问题，请提交 GitHub Issue
