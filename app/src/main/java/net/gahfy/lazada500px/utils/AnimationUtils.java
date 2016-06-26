package net.gahfy.lazada500px.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;

import net.gahfy.lazada500px.R;

/**
 * Utils about the animations.
 * @author Gaëtan HERFRAY
 */
public class AnimationUtils {
    /**
     * Fades a view in.
     * @param context Context in which the application is running
     * @param view The view to fade in.
     * @param animationEndListener to perform actions at the end of the animation
     */
    public static void fadeInView(Context context, @NonNull final View view, @Nullable final AnimationEndListener animationEndListener){
        animateView(context, view, R.anim.fade_in, animationEndListener, View.GONE, View.VISIBLE, false);
    }

    /**
     * Fades a view out.
     * @param context Context in which the application is running
     * @param view The view to fade out.
     * @param animationEndListener to perform actions at the end of the animation
     */
    public static void fadeOutView(Context context, @NonNull final View view, @Nullable final AnimationEndListener animationEndListener){
        animateView(context, view, R.anim.fade_out, animationEndListener, View.VISIBLE, View.GONE, false);
    }

    /**
     * Makes a view to enter from the right.
     * @param context Context in which the application is running
     * @param view The view to make enter from the right
     * @param animationEndListener to perform actions at the end of the animation
     */
    public static void enterView(Context context, @NonNull final View view, @Nullable final AnimationEndListener animationEndListener){
        animateView(context, view, R.anim.enter_from_right, animationEndListener, View.GONE, View.VISIBLE, true);
    }

    /**
     * Makes a view to exit to the right.
     * @param context Context in which the application is running
     * @param view The view to make exit to the right
     * @param animationEndListener to perform actions at the end of the animation
     */
    public static void exitView(Context context, @NonNull final View view, @Nullable final AnimationEndListener animationEndListener){
        animateView(context, view, R.anim.exit_to_right, animationEndListener, View.VISIBLE, View.GONE, false);
    }

    /**
     * Animates a view.
     * @param context Context in which the application is running
     * @param view The view to animate
     * @param animationResId the resId of the animation to perform
     * @param animationEndListener to perform actions at the end of the animation
     * @param visibilityCondition the visibility the view should have to perform the animation
     * @param resultVisibility the visibility the view should have at the end of the animation (or
     *                         at the beginning if view is appearing)
     * @param needInvisibleBeforeStart whether we need to set the visibility of the view to
     *                                 invisible before the animation starts (useful if the size of
     *                                 the view is required by the animation)
     */
    private static void animateView(Context context, @NonNull final View view, int animationResId, @Nullable final AnimationEndListener animationEndListener, int visibilityCondition, final int resultVisibility, boolean needInvisibleBeforeStart){
        if(view.getVisibility() == visibilityCondition) {
            if(needInvisibleBeforeStart)
                view.setVisibility(View.INVISIBLE);
            Animation viewAnimation = android.view.animation.AnimationUtils.loadAnimation(context, animationResId);
            viewAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if(resultVisibility == View.VISIBLE)
                        view.setVisibility(resultVisibility);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if(resultVisibility == View.GONE || resultVisibility == View.INVISIBLE)
                        view.setVisibility(resultVisibility);
                    if (animationEndListener != null)
                        animationEndListener.onAnimationEnd();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            view.startAnimation(viewAnimation);
        }
        else{
            if(animationEndListener != null)
                animationEndListener.onAnimationEnd();
        }
    }
}
