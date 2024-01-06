package main.java;

import java.io.Serializable;
import java.util.List;

import main.java.Enum.TypeRepas;
public class Repas implements Serializable {
    private TypeRepas typeRepas;

    public Repas(TypeRepas typeRepas) {
            this.typeRepas = typeRepas;
        }

    public TypeRepas getTypeRepas() {
        return typeRepas;
    }
}
