--DECIMAL (Digits Max, Decimal Max)
DROP TABLE Products;
DROP TABLE Employees;

CREATE TABLE Products (
    ProductID PRIMARY KEY,
    ProductName VARCHAR(100),
    Price DECIMAL(10,2),
    Stock INT,
    ReleaseData DATE
);

CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Salary DECIMAL(10, 2) CHECK (Salary > 0)
    DepartmentID INT DEFAULT 1
);

INSERT INTO Products (ProductID, ProductName, Price, Stock, ReleaseDate)
VALUES (1, 'Laptop', 999.99, 50, '2024-01-01');
VALUES (2, 'Monitor', 149.99, 20, '2024-01-14');

INSERT INTO Employees (EmployeeID, Name, Salary, DepartmentID)
VALUES (1, 'Alice Johnson', 75000, 2);