package models;

import textures.Modeltexture;

public class TexturedModel {
    private RawModel rawmodel;
    private Modeltexture texture;

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
