package com.github.alvarohsp.utils;

import org.mindrot.jbcrypt.BCrypt;
import io.quarkus.elytron.security.common.BcryptUtil;

public class Crypto {

    public static String criptografarSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt(8));
    }

    public static Boolean verificarSenha(String senhaInserida, String senhaCorreta){
        return BCrypt.checkpw(senhaInserida, senhaCorreta);
    }
}
