package com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.animation;

public class ItemAnimator extends CustomItemAnimator {

    public ItemAnimator() {
        this.setItemRemoveCustomizer(PredefinedAnimations.SHRINK);
        this.setItemAddCustomizer(PredefinedAnimations.GROW);
        this.setAddDuration(200l);
        this.setRemoveDuration(200l);
    }
}
