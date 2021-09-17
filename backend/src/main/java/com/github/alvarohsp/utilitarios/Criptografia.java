package com.github.alvarohsp.utilitarios;

import org.mindrot.jbcrypt.BCrypt;

public class Criptografia {

    public static String criptografarSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt(8));
    }

    public static Boolean verificarSenha(String senhaInserida, String senhaCorreta){
        return BCrypt.checkpw(senhaInserida, senhaCorreta);
    }
}
