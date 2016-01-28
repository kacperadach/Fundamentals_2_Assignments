// Assignment 2
// partner1-Adach partner1-Kacper
// partner1-kadach
// partner2-Boni partner2-Chase
// partner2-cboni

interface IData {
	int getValue();
	int countArgs();
	int countFuns();
	int formulaDepth(int count);
	String getFormula(int depth, int count);
}

interface IFunction {
	int getValue(Cell cellOne, Cell cellTwo);
}


class Cell {
	
	Cell(String column, int row, IData data) {
		this.row = row;
		this.column = column;
		this.data = data;
	}
	
	public String getName(int depth, int count) {
		if(count == depth) {
			return this.column + String.valueOf(row);
		}
		else {
			return this.data.getFormula(depth, count++);
		}
	}
	
	public int Value() {
		return this.data.getValue();
	}
	
	public int countArgs() {
		return this.data.countArgs();
	}
	
	public int countFuns() {
		return this.data.countFuns();
	}
	
	public int formulaDepth() {
		return this.data.formulaDepth(0);
	}
	
	public String formula(int depth) {
		return this.getName(depth, 0);
	}
}

class Number implements IData {
	
	Number(int number) {
		this.number = number;
	}
	
	public int getValue() {
		return this.number;
	}
	
	public int countArgs() {
		return 1;
	}
	
	public int countFuns() {
		return 0;
	}
	
	public int formulaDepth(int count) {
		return count;
	}
	
	public String getFormula(int depth, int count) {
		return String.valueOf(this.number);
	}
}

class Formula implements IData {
	
	Formula(IFunction function, Cell cellOne, Cell cellTwo) {
		this.function = function;
		this.cellOne = cellOne;
		this.cellTwo = cellTwo;
	}
	
	public int getValue() {
		return this.function.getValue(cellOne, cellTwo);
	}
	
	public int countArgs() {
		return this.cellOne.countArgs() + this.cellTwo.countArgs();
	}
	
	public int countFuns() {
		return 1 + this.cellOne.countFuns() + this.cellTwo.countFuns();
	}
	
	public int formulaDepth(int depth, int count) {
		value1 = this.cellOne.formulaDepth(count+1);
		value2 = this.cellTwo.formulaDepth(count+1);
		if(value1 > value2) {
			return value1;
		}
		else {
			return value2;
		}
	}
	
	public String getFormula(int depth, int count) {
		return this.formula.getName() + '(' + this.cellOne.getName(depth, count+1) + ', ' 
				+ this.cellTwo.getName(depth, count+1) + ')';	
	}
}

class Mul implements IFunction {
	
	Mul() {
		this.name = 'mul';
	}
	
	public String getName() {
		return this.name;
	}
	
	public getValue(Cell cellOne, Cell cellTwo) {
		return cellOne.value() * cellTwo.value();
	}
}

class Mod implements IFunction {
	
	Mod() {
		this.name = 'mod'
	}
	
	public getValue(Cell cellOne, Cell cellTwo) {
		return cellOne.value() % cellTwo.value();
	}
}

class Sum implements IFunction {
	
	Sum() {
		this.name = 'sum';
	}
	
	public getValue(Cell cellOne, Cell cellTwo) {
		return cellOne.value() + cellTwo.value();
	}		
}

class ExamplesExcelCells {
	IData numberTwentyFive = new Number(25);
	IData numberTen = new Number(10);
	IData numberOne = new Number(1);
	IData numberTwentySeven = new Number(27);
	IData numberSixteen = new Number(16);
	IFunction mul = new Mul();
	IFunction mod = new Mod();
	IFunction sum = new Sum();
	
	Cell cellA1 = new Cell('A', 1, numberTwentyFive);
	Cell cellB1 = new Cell('B', 1, numberTen);
	Cell cellC1 = new Cell('C', 1, numberOne);
	Cell cellD1 = new Cell('D', 1, numberTwentySeven);
	Cell cellE1 = new Cell('E', 1, numberSixteen);
	
	IData sumE1D1 = new Formula(sum, cellE1, cellD1);
	Cell cellE2 = new Cell('E', 2, sumE1D1);
	
	IData mulE1D1 = new Formula(mul, cellE1, cellD1);
	Cell cellC4 = new Cell('C', 4, mulE1D1);
	
	IData mulA1B1 = new Formula(mul, cellA1, cellB1);
	Cell cellA3 = new Cell('A', 3, mulA1B1);
	
	IData modE1A3 = new Formula(mod, cellE1, cellA3);
	Cell cellB3 = new Cell('B', 3, modE1A3);
	
	IData sumA3C1 = new Formula(sum, cellA3, cellC1);
	Cell cellC2 = new Cell('C', 2, sumA3C1);
	
	IData modC2E2 = new Formula(mod, cellC2, cellE2);
	Cell cellD2 = new Cell('D', 2, modC2E2);
	
	IData mulD2A1 = new Formula(mul, cellD2, cellA1);
	Cell cellD3 = new Cell('D', 3, mul);
	
	IData sumC4A1 = new Formula(sum, cellC4, cellA1);
	Cell cellD4 = new Cell('D', 4, sumC4A1);
	
	IData sumD4B3 = new Formula(sum, cellD4, cellB3);
	Cell cellC5 = new Cell('C', 5, sumD4B3);
	
	IData modD3C5 = new Formula(mod, cellD3, cellC5);
	Cell cellA5 = new Cell('A', 5, modD3C5);
	
	// IFunction mul = new Mul();
	// IFunction mod = new Mod();
	// IFunction sum = new Sum();
	
}


