package models;

import textures.Modeltexture;

public class TexturedModel {
    private final RawModel rawmodel;
    private final Modeltexture texture;

    public TexturedModel(RawModel model, Modeltexture texture){
        this.rawmodel = model;
        this.texture = texture;
    }

    public RawModel getRawmodel() {
        return rawmodel;
    }

    public Modeltexture getTexture() {
        return texture;
    }
}
