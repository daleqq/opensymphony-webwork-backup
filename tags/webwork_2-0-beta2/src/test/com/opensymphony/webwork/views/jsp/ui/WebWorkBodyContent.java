/*
 * Copyright (c) 2002-2003 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.webwork.views.jsp.ui;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;


/**
 * WebWorkBodyContent
 * User: jcarreira
 * Created: Oct 17, 2003 11:18:58 PM
 */
public class WebWorkBodyContent extends BodyContent {
    //~ Instance fields ////////////////////////////////////////////////////////

    private StringBuffer buffer = new StringBuffer();

    //~ Constructors ///////////////////////////////////////////////////////////

    public WebWorkBodyContent(JspWriter jspWriter) {
        super(jspWriter);
    }

    //~ Methods ////////////////////////////////////////////////////////////////

    public Reader getReader() {
        return new StringReader(buffer.toString());
    }

    public int getRemaining() {
        return 0;
    }

    public String getString() {
        return buffer.toString();
    }

    public void clear() throws IOException {
        buffer = new StringBuffer();
    }

    public void clearBuffer() throws IOException {
        clear();
    }

    public void close() throws IOException {
        buffer = null;
    }

    public void newLine() throws IOException {
        buffer.append("\n");
    }

    public void print(boolean b) throws IOException {
        buffer.append(b);
    }

    public void print(char c) throws IOException {
        buffer.append(c);
    }

    public void print(int i) throws IOException {
        buffer.append(i);
    }

    public void print(long l) throws IOException {
        buffer.append(l);
    }

    public void print(float v) throws IOException {
        buffer.append(v);
    }

    public void print(double v) throws IOException {
        buffer.append(v);
    }

    public void print(char[] chars) throws IOException {
        buffer.append(chars);
    }

    public void print(String s) throws IOException {
        buffer.append(s);
    }

    public void print(Object o) throws IOException {
        buffer.append(o);
    }

    public void println() throws IOException {
        newLine();
    }

    public void println(boolean b) throws IOException {
        print(b);
        newLine();
    }

    public void println(char c) throws IOException {
        print(c);
        newLine();
    }

    public void println(int i) throws IOException {
        print(i);
        newLine();
    }

    public void println(long l) throws IOException {
        print(l);
        newLine();
    }

    public void println(float v) throws IOException {
        print(v);
        newLine();
    }

    public void println(double v) throws IOException {
        print(v);
        newLine();
    }

    public void println(char[] chars) throws IOException {
        print(chars);
        newLine();
    }

    public void println(String s) throws IOException {
        print(s);
        newLine();
    }

    public void println(Object o) throws IOException {
        print(o);
        newLine();
    }

    /**
     * Write a portion of an array of characters.
     *
     * @param  cbuf  Array of characters
     * @param  off   Offset from which to start writing characters
     * @param  len   Number of characters to write
     *
     * @exception  IOException  If an I/O error occurs
     */
    public void write(char[] cbuf, int off, int len) throws IOException {
        buffer.append(cbuf, off, len);
    }

    public void writeOut(Writer writer) throws IOException {
        writer.write(buffer.toString());
    }
}
