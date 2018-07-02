/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adisys.server.utility;

import java.util.ArrayList;

/**
 * The Class UIFeedbackException.
 * Classe pubblica che, attraverso i suoi metodi, 
 * gestisce le eccezioni a basso livello. Può essere 
 * lanciata da qualsiasi livello. Questa classe, 
 * invia una stringa di testo, che ritornerà al metodo 
 * usato e che verrà elaborata in seguito nella classe 
 * FC, in cui sono gestiti i metodi “messagesBundle” 
 * e “VisualizzaMessaggioPupop”
 * 
 */
@SuppressWarnings("serial")
public class UIFeedbackException extends Exception {

	/** The msg. */
	private static String msg;

	/** The msg type. */
	private static MsgType msgType;

	/** The replacement array. */
	private static ArrayList<String> replacementArray;

	/**
	 * The Enum MsgType.
	 */
	public enum MsgType {

		/** The empty. */
		EMPTY,
		/** The notify. */
		NOTIFY,
		/** The warning. */
		WARNING,
		/** The error. */
		ERROR
	};

	/**
	 * Instantiates a new UI feedback exception.
	 * 
	 * @param msgCode
	 *            the msg code
	 */
	public UIFeedbackException(String msgCode) {
		super(
				"User interface feedback prompt (without replacementArray)************************");
		UIFeedbackException.msg = msgCode;
		UIFeedbackException.msgType = MsgType.EMPTY;
		UIFeedbackException.replacementArray = new ArrayList<String>();
	}

	/**
	 * Instantiates a new UI feedback exception.
	 * 
	 * @param msgCode
	 *            the msg code
	 * @param replacementArray
	 *            the replacement array
	 */
	public UIFeedbackException(String msgCode,
			ArrayList<String> replacementArray) {
		super(
				"User interface feedback prompt (with replacementArray)************************");
		UIFeedbackException.msg = msgCode;
		UIFeedbackException.msgType = MsgType.EMPTY;
		UIFeedbackException.replacementArray = replacementArray;
	}

	/**
	 * Instantiates a new UI feedback exception.
	 * 
	 * @param msgCode
	 *            the msg code
	 * @param type
	 *            the type
	 */
	public UIFeedbackException(String msgCode, MsgType type) {
		super(
				"User interface feedback prompt (without replacementArray)************************");
		UIFeedbackException.msg = msgCode;
		UIFeedbackException.msgType = type;
		UIFeedbackException.replacementArray = new ArrayList<String>();
	}

	/**
	 * Instantiates a new UI feedback exception.
	 * 
	 * @param msgCode
	 *            the msg code
	 * @param replacementArray
	 *            the replacement array
	 * @param type
	 *            the type
	 */
	public UIFeedbackException(String msgCode,
			ArrayList<String> replacementArray, MsgType type) {
		super(
				"User interface feedback prompt (with replacementArray)************************");
		UIFeedbackException.msg = msgCode;
		UIFeedbackException.msgType = type;
		UIFeedbackException.replacementArray = replacementArray;
	}

	/**
	 * Gets the msg.
	 * 
	 * @return the msg
	 */
	public static String getMsg() {
		return UIFeedbackException.msg;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public static MsgType getType() {
		return UIFeedbackException.msgType;
	}

	/**
	 * Gets the replacement array.
	 * 
	 * @return the replacement array
	 */
	public static ArrayList<String> getReplacementArray() {
		return UIFeedbackException.replacementArray;
	}
}
