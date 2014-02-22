package net.foreworld.rss2.monitor;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.foreworld.rss2.utils.StringUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;

/**
 * �����������
 * 
 * @author ���
 * 
 */
public class ClipBoardMonitor {

	private static final int INTERVAL = 500;

	private Display display;

	private Clipboard clipboard;

	TextTransfer textTransfer;

	private boolean run = false;
	//���а�����һ���ַ���
	private String last;

	private static ClipBoardMonitor instance = new ClipBoardMonitor();

	private List listeners = new ArrayList();

	private void notifyListeners(String text) {
		for (Iterator it = listeners.iterator(); it.hasNext();) {
			IClipboardChangeListener listener = (IClipboardChangeListener) it
					.next();
			listener.clipboardChange(text);
		}
	}

	private ClipBoardMonitor() {
		this.display = Display.getDefault();
		this.clipboard = new Clipboard(display);
		this.textTransfer = TextTransfer.getInstance();
	}

	UIJob job = new UIJob("ClipBoardMonitorUIJob") {
		
		public IStatus runInUIThread(IProgressMonitor monitor) {
			String textData = (String) clipboard.getContents(textTransfer);
			if (!StringUtils.isEmpty(textData) && !textData.equals(last)) {
				last = textData;
				notifyListeners(last);
			}
			if (run)
				this.schedule(INTERVAL);
			return Status.OK_STATUS;
		}
	};

	public void start() {
		if (!run) {
			run = true;
			// ���job�Ѿ����ھͲ���Ҫ�ٴ���������ֹ�û��������û��߽��ü�����������������������
			if (job.getState() == Job.NONE) {
				job.schedule(INTERVAL);
			}
		}
	}

	public void stop() {
		if (run) {
			run = false;
		}
	}

	public static ClipBoardMonitor getInstance() {
		return instance;
	}

	public void addClipboardChangeListener(IClipboardChangeListener listener) {
		this.listeners.add(listener);
	}

	public void removeClipboardChangeListener(IClipboardChangeListener listener) {
		this.listeners.remove(listener);
	}

	public String getLast() {
		if(this.last == null)
			return "";
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

}