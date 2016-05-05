package com.michaelfotiadis.crossyscore.core.data.parsers.gson;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.common.models.mascot.MascotImpl;
import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.common.models.player.PlayerImpl;
import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.common.models.score.ScoreImpl;

import java.util.HashSet;
import java.util.Set;

/* package */ final class TypeAdapterFactoryCreator {

    private static final String JSON_TYPE_FIELD = "gsonObjectType";
    private final Set<RuntimeTypeAdapterFactory> adapters;
    private final Set<Class<?>> baseTypes = new HashSet<>();
    private final Set<Class<?>> subTypes = new HashSet<>();

    public TypeAdapterFactoryCreator() {
        adapters = new HashSet<>();

        adapters.add(create(Score.class, ScoreImpl.class));
        adapters.add(create(Player.class, PlayerImpl.class));
        adapters.add(create(Mascot.class, MascotImpl.class));

    }

    private <T> RuntimeTypeAdapterFactory create(final Class<T> baseType,
                                                 final Class<? extends T> subType) {
        final String subTypeName = subType.getSimpleName();

        if (!baseTypes.add(baseType)) {
            throw new IllegalStateException("You have already added BaseType " + baseType.getName());
        }

        if (!subTypes.add(subType)) {
            throw new IllegalStateException("You have already added SubType " + subType.getName());
        }

        return RuntimeTypeAdapterFactory
                .of(baseType, subTypeName, JSON_TYPE_FIELD)
                .registerSubtype(subType, subTypeName);
    }


    /**
     * Generates a collection of {@link RuntimeTypeAdapterFactory}
     *
     * @return {@link Set} of {@link RuntimeTypeAdapterFactory}
     */
    public Set<RuntimeTypeAdapterFactory> getAdapters() {
        return adapters;
    }

}