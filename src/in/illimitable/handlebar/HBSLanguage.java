/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package in.illimitable.handlebar;

import org.netbeans.api.lexer.Language;
import org.netbeans.modules.csl.spi.DefaultLanguageConfig;
import org.netbeans.modules.csl.spi.LanguageRegistration;

import in.illimitable.handlebar.lexer.api.HBSTokenId;


@LanguageRegistration(mimeType="application/x-hbs")
public class HBSLanguage extends DefaultLanguageConfig {
    
    public HBSLanguage() {
    }

    @Override
    public Language getLexerLanguage() {
        return HBSTokenId.language();
    }
    
    @Override
    public String getDisplayName() {
        return "HBS";
    }
    
    @Override
    public String getPreferredExtension() {
        return "hbs"; // NOI18N
    }

}
