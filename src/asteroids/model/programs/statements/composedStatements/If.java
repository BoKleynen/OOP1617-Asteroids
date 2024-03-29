package asteroids.model.programs.statements.composedStatements;

import asteroids.model.programs.Parent;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.statements.Statement;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author  Bo Kleynen & Yrjo Koyen
 *
 * TODO: implement expressions so they can be converted to boolean types
 */
public class If<T extends Parent<T>> extends Statement<T> {

    public If(Expression<Boolean> condition, Statement<T> ifBody, Statement<T> elseBody) {
        this.condition = condition;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }

    private Expression<Boolean> condition;
    private Statement<T> ifBody;
    private Statement<T> elseBody;

    public Statement<T> getIfBody() {
        return ifBody;
    }

    public Statement<T> getElseBody() {
        return elseBody;
    }

    private Iterator<Statement<T>> bodyIterator;

    @Override
    public void execute() {
        if (condition.getValue()) {
            bodyIterator = ifBody.iterator();
        }

        else if (elseBody != null) {
            bodyIterator = elseBody.iterator();
        }

        while (bodyIterator != null && bodyIterator.hasNext()) {
            bodyIterator.next().execute();
        }
    }

    @Override
    public void setParent(T parent) {
        super.setParent(parent);
        ifBody.setParent(parent);

        if (elseBody != null)
            elseBody.setParent(parent);
    }

    @Override
    public boolean isValidFunctionStatement() {
        return ifBody.isValidFunctionStatement() && (elseBody == null || elseBody.isValidFunctionStatement());
    }

    @Override
    public Iterator<Statement<T>> iterator() {
        condition.setStatement(If.this);
        return new Iterator<Statement<T>>() {

            Iterator<Statement<T>> bodyIterator;

            @Override
            public boolean hasNext() {
                if (bodyIterator != null)
                    return bodyIterator.hasNext();

                else if (condition.getValue()) {
                    bodyIterator = ifBody.iterator();
                    return bodyIterator.hasNext();
                }

                else if (elseBody != null) {
                    bodyIterator = elseBody.iterator();
                    return bodyIterator.hasNext();
                }

                else
                    return false;
            }

            @Override
            public Statement<T> next() {
                if (! hasNext())
                    throw new NoSuchElementException();

                return bodyIterator.next();
            }
        };
    }

    @Override
    public Statement<T> clone() throws CloneNotSupportedException {
        return new If<>(condition.clone(), ifBody.clone(), elseBody == null ? null : elseBody.clone());
    }
}
