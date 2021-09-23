package net.danielgolan.elderion;

import net.minecraft.util.Identifier;

public record ElderionIdentifier(Author author, String path) {
    public ElderionIdentifier(Author author, String path) {
        this.author = author;
        this.path = path.toLowerCase();
    }

    public Identifier toIdentifier() {
        return new Identifier(author.modID(), author.name() + path);
    }

    public Identifier toIdentifier(String pathAddition) {
        return new Identifier(author.modID(), author.name() + path + pathAddition);
    }
}
