/**
 * Project Name : dev-utils<br>
 * File Name : RuleHandlingException.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/17<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */
package org.thinkit.common.rule.exception;

/**
 * Thrown to indicate that an incorrect operation or incorrect value has been
 * detected when performing rule processing.
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */

public final class RuleHandlingException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 0L;

    /**
     * Constructs an <code>RuleHandlingException</code> with no detail message.
     */
    public RuleHandlingException() {
        super();
    }

    /**
     * Constructs an <code>RuleHandlingException</code> with the specified detail
     * message.
     *
     * @param s the detail message.
     */
    public RuleHandlingException(String s) {
        super(s);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * <p>
     * Note that the detail message associated with <code>cause</code> is <i>not</i>
     * automatically incorporated in this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the
     *                {@link Throwable#getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link Throwable#getCause()} method). (A <tt>null</tt> value
     *                is permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.0
     */
    public RuleHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message of
     * <tt>(cause==null ? null : cause.toString())</tt> (which typically contains
     * the class and detail message of <tt>cause</tt>). This constructor is useful
     * for exceptions that are little more than wrappers for other throwables (for
     * example, {@link java.security.PrivilegedActionException}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link Throwable#getCause()} method). (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.0
     */
    public RuleHandlingException(Throwable cause) {
        super(cause);
    }
}
