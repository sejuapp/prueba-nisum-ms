--agregamos parametro para validar email
INSERT INTO PARAMETER (PARAMETER_NAME, PARAMETER_VALUE) VALUES('EMAIL', '[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+');
INSERT INTO PARAMETER (PARAMETER_NAME, PARAMETER_VALUE) VALUES('PASSWORD', '^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$');