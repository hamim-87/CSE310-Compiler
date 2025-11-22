# HTML Tag Validator

This project provides a lexer for validating the proper nesting of HTML-style tags. It ensures that each opening tag has a corresponding closing tag in the correct order. The supported tags are `<html>`, `<head>`, `<body>`, and `<p>`.

## Features

- Validates the nesting of HTML tags.
- Supports the following tags:
  - `<html>`
  - `<head>`
  - `<body>`
  - `<p>`
- Outputs messages indicating whether the structure is valid or invalid.

## Usage

1. **Clone the repository:**
   ```
   git clone <repository-url>
   cd html-tag-validator
   ```

2. **Compile the lexer:**
   Use a tool like `flex` to compile the `tag_validator.l` file:
   ```
   flex src/tag_validator.l
   gcc lex.yy.c -o tag_validator -lfl
   ```

3. **Run the validator:**
   Provide an HTML file as input:
   ```
   ./tag_validator <input-file.html>
   ```

## Examples

### Valid Input
```html
<html>
    <head>
        <title>Test</title>
    </head>
    <body>
        <p>Hello, World!</p>
    </body>
</html>
```
**Output:**
```
Valid HTML structure.
```

### Invalid Input
```html
<html>
    <head>
        <title>Test</title>
    </body>
</html>
```
**Output:**
```
Invalid HTML structure: mismatched tags.
```

## License

This project is licensed under the MIT License.