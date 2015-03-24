package pictureManage;

import java.io.File;
import java.io.FileFilter;

public class HiddenFileFilter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		return !pathname.isHidden();
	}


}
						