package com.myfvn.ui.views;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.foreworld.utils.StringMatcher;
import net.foreworld.utils.rcp.core.persist.IEntity;
import net.foreworld.utils.rcp.core.persist.IParent;
import net.foreworld.utils.rcp.core.persist.events.AbstractModelEvent;

import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.myfvn.core.persist.IAttachment;
import com.myfvn.core.persist.IEmail;
import com.myfvn.core.persist.IEmailbox;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * 
 */
public class FileNavigatorFilter extends ViewerFilter {

	/** Possible Filter Values */
	public enum Type {

		/** Show all Feeds */
		SHOW_ALL,

		SHOW_MAIL,

		SHOW_ATTACHMENT
	}

	/* Current Filter Value */
	private Type _type = Type.SHOW_ALL;

	Type getType() {
		return _type;
	}

	public void setType(Type $type) {
		if (_type != $type)
			_type = $type;
	}

	/** Possible Search Targets */
	public enum SearchTarget {

		/** Search Name */
		NAME,

		CREATETIME
	}

	/* The string pattern matcher used for this pattern filter */
	private StringMatcher _matcher;

	/* Current Search Target */
	private SearchTarget _searchTarget = SearchTarget.NAME;

	SearchTarget getSearchTarget() {
		return _searchTarget;
	}

	public void setSearchTarget(SearchTarget $searchTarget) {
		_searchTarget = $searchTarget;
	}

	@Override
	public final boolean select(Viewer $viewer, Object $parentElement, Object $element) {
		/* Filter not Active */
		if (_matcher == null && _type == Type.SHOW_ALL)
			return true;
		return isElementVisible($viewer, $element);
	}

	@Override
	public Object[] filter(Viewer $viewer, Object $parent, Object[] $elements) {
		/* Filter not Active */
		if (_matcher == null && _type == FileNavigatorFilter.Type.SHOW_ALL)
			return $elements;
		return super.filter($viewer, $parent, $elements);
	}

	@Override
	public boolean isFilterProperty(Object $element, String $property) {
		return false; // This is handled in needsRefresh() already
	}

	boolean isElementVisible(Viewer $viewer, Object $element) {
		return isParentMatch($viewer, $element) || isLeafMatch($viewer, $element);
	}

	private boolean isParentMatch(Viewer $viewer, Object $element) {
		if (!($viewer instanceof AbstractTreeViewer))
			return false;

		Object[] __children = ((ITreeContentProvider) ((AbstractTreeViewer) $viewer).getContentProvider()).getChildren($element);

		if ((__children != null) && (__children.length > 0))
			return filter($viewer, $element, __children).length > 0;

		return false;
	}

	protected boolean isLeafMatch(Viewer $viewer, Object $element) {
		if ($element instanceof IParent) {
			IParent __entity = (IParent) $element;
			boolean __isMatch = false;

			/* Look at Type */
			switch (_type) {

			case SHOW_ALL:
				__isMatch = true;
				break;

			case SHOW_MAIL:
				__isMatch = $element instanceof IEmail;
				break;

			case SHOW_ATTACHMENT:
				__isMatch = $element instanceof IAttachment;
				break;
			}

			/* Finally check the Pattern */
			if (__isMatch && _matcher != null) {
				if (!wordMatches(__entity) && !wordMatches(__entity.getParent())) {
					return false;
				}
			}
			return __isMatch;
		}
		return false;
	}

	private String[] getWords(String $text) {
		List<String> __words = new ArrayList<String>();

		/*
		 * Break the text up into words, separating based on whitespace and
		 * common punctuation. Previously used String.split(..., "\\W"), where
		 * "\W" is a regular expression (see the Javadoc for class Pattern).
		 * Need to avoid both String.split and regular expressions, in order to
		 * compile against JCL Foundation (bug 80053). Also need to do this in
		 * an NL-sensitive way. The use of BreakIterator was suggested in bug
		 * 90579.
		 */
		BreakIterator __iter = BreakIterator.getWordInstance();
		__iter.setText($text);
		int __i = __iter.first();
		while (__i != java.text.BreakIterator.DONE && __i < $text.length()) {
			int ___j = __iter.following(__i);
			if (___j == java.text.BreakIterator.DONE)
				___j = $text.length();

			/* match the word */
			if (Character.isLetterOrDigit($text.charAt(__i))) {
				String ____word = $text.substring(__i, ___j);
				__words.add(____word);
			}
			__i = ___j;
		}
		return __words.toArray(new String[__words.size()]);
	}

	private boolean match(String $string) {
		if (_matcher == null)
			return true;

		return _matcher.match($string);
	}

	protected boolean wordMatches(String $text) {
		if ($text == null)
			return false;

		/* If the whole text matches we are all set */
		if (match($text))
			return true;

		/* Otherwise check if any of the words of the text matches */
		String[] __words = getWords($text);
		for (String ___word : __words) {
			if (match(___word))
				return true;
		}
		return false;
	}

	boolean isElementSelectable(Object $element) {
		return $element != null;
	}

	public void setPattern(String $patternString) {
		if ($patternString == null || "".equals($patternString)) //$NON-NLS-1$
			_matcher = null;
		else
			_matcher = new StringMatcher($patternString + "*", true, false); //$NON-NLS-1$
	}

	boolean needsRefresh(Class<? extends IEntity> $entityClass, Set<? extends AbstractModelEvent> $events) {
		return needsRefresh($entityClass, $events, false);
	}

	boolean needsRefresh(Class<? extends IEntity> $entityClass, Set<? extends AbstractModelEvent> $events, boolean $searchResultsChanged) {
		/* In case the Filter is not active at all */
		if (_matcher == null && _type == Type.SHOW_ALL)
			return false;
		return true;
	}

	private boolean wordMatches(IParent $node) {

		/* Return early if node is a Mail-Set */
		if ($node.getParent() instanceof IEmailbox || $node.getParent() == null)
			return false;

		/* Search Name */
		if (_searchTarget == SearchTarget.NAME)
			return wordMatches($node.getName());

		/* Search CreateTime */
		if (_searchTarget == SearchTarget.CREATETIME && $node instanceof IParent) {

		}

		return false;
	}

}
