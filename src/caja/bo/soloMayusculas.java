/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author juan carlos
 */

public class soloMayusculas extends PlainDocument{
    
    @Override
    public void insertString(int offset, String str, AttributeSet atrr) throws BadLocationException{
        super.insertString(offset, str.toUpperCase(), atrr);
    }
    
}
