package control;

import java.util.ListResourceBundle;

public class Dictionary_en extends ListResourceBundle{
	Object obj[][]= {
			{"userid","User ID"},
			{"password","Password"}
	};
	@Override
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		return obj;
	}

}
