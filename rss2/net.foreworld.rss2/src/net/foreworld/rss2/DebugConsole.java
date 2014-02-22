package net.foreworld.rss2;

import java.io.PrintStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * This class is used to view application generated output to stdout and 
 * stderr.
 * 
 * @author mms
 *
 */
public class DebugConsole extends MessageConsole{
	private MessageConsoleStream outMessageStream;
	private MessageConsoleStream errMessageStream;

	public DebugConsole(){
		super("stdio/stderr", null);

		outMessageStream = newMessageStream();
		outMessageStream.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));

		errMessageStream = newMessageStream();
		errMessageStream.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_RED));

		System.setOut(new PrintStream(outMessageStream));
		System.setErr(new PrintStream(errMessageStream));

	}
}
