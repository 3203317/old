package net.foreworld.utils;

import java.util.Vector;

/**
 * 
 * @author huangxin (huangxin@foreworld.net)
 * @desc A string pattern matcher, suppporting "*" and "?" wildcards.
 */
public class StringMatcher {
	private final String _pattern;
	private final int _length; // pattern length
	private final boolean _ignoreWildCards;
	private final boolean _ignoreCase;
	private boolean _hasLeadingStar;
	private boolean _hasTrailingStar;
	private String _segments[]; // the given pattern is split into * separated
	// segments

	/* boundary value beyond which we don't need to search in the text */
	private int _bound = 0;

	private static final char _singleWildCard = '\u0000';

	/**
	 * StringMatcher constructor takes in a String object that is a simple
	 * pattern which may contain '*' for 0 and many characters and '?' for
	 * exactly one character. Literal '*' and '?' characters must be escaped in
	 * the pattern e.g., "\*" means literal "*", etc. Escaping any other
	 * character (including the escape character itself), just results in that
	 * character in the pattern. e.g., "\a" means "a" and "\\" means "\" If
	 * invoking the StringMatcher with string literals in Java, don't forget
	 * escape characters are represented by "\\".
	 * 
	 * @param $pattern
	 *            the pattern to match text against
	 * @param $ignoreCase
	 *            if true, case is ignored
	 * @param $ignoreWildCards
	 *            if true, wild cards and their escape sequences are ignored
	 *            (everything is taken literally).
	 */
	public StringMatcher(String $pattern, boolean $ignoreCase, boolean $ignoreWildCards) {
		if ($pattern == null) {
			throw new IllegalArgumentException();
		}
		_ignoreCase = $ignoreCase;
		_ignoreWildCards = $ignoreWildCards;
		_pattern = $pattern;
		_length = $pattern.length();

		if (_ignoreWildCards) {
			parseNoWildCards();
		} else {
			parseWildCards();
		}
	}

	/**
	 * match the given <code>text</code> with the pattern
	 * 
	 * @return true if matched eitherwise false
	 * @param $text
	 *            a String object
	 */
	public boolean match(String $text) {
		if ($text == null) {
			return false;
		}
		return match($text, 0, $text.length());
	}

	/**
	 * Given the starting (inclusive) and the ending (exclusive) positions in
	 * the <code>text</code>, determine if the given substring matches with
	 * aPattern
	 * 
	 * @return true if the specified portion of the text matches the pattern
	 * @param $text
	 *            a String object that contains the substring to match
	 * @param $start
	 *            marks the starting position (inclusive) of the substring
	 * @param $end
	 *            marks the ending index (exclusive) of the substring
	 */
	public boolean match(String $text, int $start, int $end) {
		if (null == $text) {
			throw new IllegalArgumentException();
		}

		if ($start > $end) {
			return false;
		}

		if (_ignoreWildCards) {
			return ($end - $start == _length) && _pattern.regionMatches(_ignoreCase, 0, $text, $start, _length);
		}
		int __segCount = _segments.length;
		if (__segCount == 0 && (_hasLeadingStar || _hasTrailingStar)) {
			return true;
		}
		if ($start == $end) {
			return _length == 0;
		}
		if (_length == 0) {
			return $start == $end;
		}

		int __tlen = $text.length();
		if ($start < 0) {
			$start = 0;
		}
		if ($end > __tlen) {
			$end = __tlen;
		}

		int __tCurPos = $start;
		int __bound = $end - _bound;
		if (__bound < 0) {
			return false;
		}
		int __i = 0;
		String __current = _segments[__i];
		int __segLength = __current.length();

		/* process first segment */
		if (!_hasLeadingStar) {
			if (!regExpRegionMatches($text, $start, __current, 0, __segLength))
				return false;
			++__i;
			__tCurPos = __tCurPos + __segLength;
		}
		if ((_segments.length == 1) && (!_hasLeadingStar) && (!_hasTrailingStar)) {
			// only one segment to match, no wildcards specified
			return __tCurPos == $end;
		}
		/* process middle segments */
		while (__i < __segCount) {
			__current = _segments[__i];
			int ___currentMatch;
			int ___k = __current.indexOf(_singleWildCard);
			if (___k < 0) {
				___currentMatch = textPosIn($text, __tCurPos, $end, __current);
				if (___currentMatch < 0) {
					return false;
				}
			} else {
				___currentMatch = regExpPosIn($text, __tCurPos, $end, __current);
				if (___currentMatch < 0) {
					return false;
				}
			}
			__tCurPos = ___currentMatch + __current.length();
			__i++;
		}

		/* process final segment */
		if (!_hasTrailingStar && __tCurPos != $end) {
			int ___clen = __current.length();
			return regExpRegionMatches($text, $end - ___clen, __current, 0, ___clen);
		}
		return __i == __segCount;
	}

	/**
	 * This method parses the given pattern into segments seperated by wildcard
	 * '*' characters. Since wildcards are not being used in this case, the
	 * pattern consists of a single segment.
	 */
	private void parseNoWildCards() {
		_segments = new String[1];
		_segments[0] = _pattern;
		_bound = _length;
	}

	/**
	 * Parses the given pattern into segments seperated by wildcard '*'
	 * characters.
	 * 
	 * @param p
	 *            a String object that is a simple regular expression with '*'
	 *            and/or '?'
	 */
	@SuppressWarnings("unchecked")
	private void parseWildCards() {
		if (_pattern.startsWith("*")) { //$NON-NLS-1$
			_hasLeadingStar = true;
		}
		if (_pattern.endsWith("*")) {//$NON-NLS-1$
			/* make sure it's not an escaped wildcard */
			if (_length > 1 && _pattern.charAt(_length - 2) != '\\') {
				_hasTrailingStar = true;
			}
		}

		Vector __temp = new Vector();

		int __pos = 0;
		StringBuilder __buf = new StringBuilder();
		while (__pos < _length) {
			char ___c = _pattern.charAt(__pos++);
			switch (___c) {
			case '\\':
				if (__pos >= _length) {
					__buf.append(___c);
				} else {
					char ____next = _pattern.charAt(__pos++);
					/* if it's an escape sequence */
					if (____next == '*' || ____next == '?' || ____next == '\\') {
						__buf.append(____next);
					} else {
						/* not an escape sequence, just insert literally */
						__buf.append(___c);
						__buf.append(____next);
					}
				}
				break;
			case '*':
				if (__buf.length() > 0) {
					/* new segment */
					__temp.addElement(__buf.toString());
					_bound += __buf.length();
					__buf.setLength(0);
				}
				break;
			case '?':
				/* append special character representing single match wildcard */
				__buf.append(_singleWildCard);
				break;
			default:
				__buf.append(___c);
			}
		}

		/* add last buffer to segment list */
		if (__buf.length() > 0) {
			__temp.addElement(__buf.toString());
			_bound += __buf.length();
		}

		_segments = new String[__temp.size()];
		__temp.copyInto(_segments);
	}

	/**
	 * @param $text
	 *            a string which contains no wildcard
	 * @param $start
	 *            the starting index in the text for search, inclusive
	 * @param $end
	 *            the stopping point of search, exclusive
	 * @return the starting index in the text of the pattern , or -1 if not
	 *         found
	 */
	protected int posIn(String $text, int $start, int $end) {// no wild card
		// in pattern
		int __max = $end - _length;

		if (!_ignoreCase) {
			int ___i = $text.indexOf(_pattern, $start);
			if (___i == -1 || ___i > __max) {
				return -1;
			}
			return ___i;
		}

		for (int i = $start; i <= __max; ++i) {
			if ($text.regionMatches(true, i, _pattern, 0, _length)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * @param $text
	 *            a simple regular expression that may only contain '?'(s)
	 * @param $start
	 *            the starting index in the text for search, inclusive
	 * @param $end
	 *            the stopping point of search, exclusive
	 * @param $p
	 *            a simple regular expression that may contains '?'
	 * @return the starting index in the text of the pattern , or -1 if not
	 *         found
	 */
	protected int regExpPosIn(String $text, int $start, int $end, String $p) {
		int __plen = $p.length();

		int __max = $end - __plen;
		for (int ___i = $start; ___i <= __max; ++___i) {
			if (regExpRegionMatches($text, ___i, $p, 0, __plen)) {
				return ___i;
			}
		}
		return -1;
	}

	/**
	 * @return boolean
	 * @param $text
	 *            a String to match
	 * @param $tStart
	 *            int that indicates the starting index of match, inclusive
	 * @param $p
	 *            String, String, a simple regular expression that may contain
	 *            '?'
	 * @param $pStart
	 * @param $plen
	 */
	protected boolean regExpRegionMatches(String $text, int $tStart, String $p, int $pStart, int $plen) {
		while ($plen-- > 0) {
			char ___tchar = $text.charAt($tStart++);
			char ___pchar = $p.charAt($pStart++);

			/* process wild cards */
			if (!_ignoreWildCards) {
				/* skip single wild cards */
				if (___pchar == _singleWildCard) {
					continue;
				}
			}
			if (___pchar == ___tchar) {
				continue;
			}
			if (_ignoreCase) {
				if (Character.toUpperCase(___tchar) == Character.toUpperCase(___pchar)) {
					continue;
				}
				// comparing after converting to upper case doesn't handle all
				// cases;
				// also compare after converting to lower case
				if (Character.toLowerCase(___tchar) == Character.toLowerCase(___pchar)) {
					continue;
				}
			}
			return false;
		}
		return true;
	}

	/**
	 * @param $text
	 *            the string to match
	 * @param $start
	 *            the starting index in the text for search, inclusive
	 * @param $end
	 *            the stopping point of search, exclusive
	 * @param $p
	 *            a string that has no wildcard
	 * @return the starting index in the text of the pattern , or -1 if not
	 *         found
	 */
	protected int textPosIn(String $text, int $start, int $end, String $p) {

		int __plen = $p.length();
		int __max = $end - __plen;

		if (!_ignoreCase) {
			int ___i = $text.indexOf($p, $start);
			if (___i == -1 || ___i > __max) {
				return -1;
			}
			return ___i;
		}

		for (int ___i = $start; ___i <= __max; ++___i) {
			if ($text.regionMatches(true, ___i, $p, 0, __plen)) {
				return ___i;
			}
		}

		return -1;
	}
}
