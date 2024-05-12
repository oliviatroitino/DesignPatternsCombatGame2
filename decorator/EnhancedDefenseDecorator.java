package decorator;

import strategy.ActionStrategy;
import factory.Entity;

/* Decorador concreto */
public class EnhancedDefenseDecorator extends ActionDecorator {

    public EnhancedDefenseDecorator(ActionStrategy decoratedAction) {
        super(decoratedAction);
    }

    @Override 
    public void action(Entity target) {
        System.out.println("Aumentando la defensa del objetivo.");
        super.action(target);
    }
    
}
