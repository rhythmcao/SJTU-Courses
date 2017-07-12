package util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * UI tool class
 * @author rhythmCao
 *
 */
public class Frameutil {

	/**
	 * calculate middle point position
	 */
	public static Point getMiddlePoint(int x, int y) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		double width = dimension.getWidth();
		double height = dimension.getHeight();
		Point point = new Point();
		point.setLocation((width - x) / 2, (height - y) / 2 - 100);
		return point;
	}
}
