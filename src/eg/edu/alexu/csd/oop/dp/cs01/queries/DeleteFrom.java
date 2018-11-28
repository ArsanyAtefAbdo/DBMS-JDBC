package eg.edu.alexu.csd.oop.dp.cs01.queries;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.cs01.condition.RelationalCondition;
import eg.edu.alexu.csd.oop.db.cs01.condition.RelationalSolver;
import eg.edu.alexu.csd.oop.db.cs01.fileManager.FileManager;
import eg.edu.alexu.csd.oop.db.cs01.modules.Row;
import eg.edu.alexu.csd.oop.db.cs01.modules.Table;

public class DeleteFrom extends OurQuery {

	public DeleteFrom(Table table, RelationalCondition condition) {
		setTable(table);
		setCondition(condition);
	}
	
	@Override
	public int execute2() {
		FileManager.getInstance().readTable(getTable());
		if (getCondition() == null) {
			int effectedRows = getTable().getRows().size(); 
			getTable().setRows(null);
			return effectedRows;
		}else if (getCondition() != null) {
			ArrayList<Row> remainingRows = new ArrayList<>();
			for (Row r:getTable().getRows()) {
				if (!RelationalSolver.getInstance().isRowSolvingCondition(r, getCondition())) {
					remainingRows.add(r);
				}
			}
			getTable().setRows(remainingRows);
		}
		
		return 0;
	}

}
