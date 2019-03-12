package com.scg.domain;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.util.PersonalName;

/**
 * A consultant for the SCG, just has a PersonalName.
 * 
 * @author Norman Skinner
 *
 */
public class Consultant implements Serializable, Comparable<Consultant> {
    private static final long serialVersionUID = -5135884238966407718L;
    private static final Logger log = LoggerFactory.getLogger(Consultant.class);
    private PersonalName name;

    private static Comparator<Consultant> natraulOrderComparator = Comparator.comparing(Consultant::getName);

    /**
     * Creates a new instance of Consultant.
     * 
     * @param name
     *            the consultant's name.
     */
    public Consultant(PersonalName name) {
        super();
        this.name = name;
    }

    /**
     * Getter for name property.
     * 
     * @return value of name property.
     */
    public PersonalName getName() {
        return name;
    }

    /**
     * Returns the string representation of the consultant's name.
     * 
     * @return the consultant name string
     */
    @Override
    public String toString() {
        return getName().toString();
    }

    /**
     * Compares this Consultant object with the specified object for order. Returns
     * a negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object, the consultant name is used
     * to perform the comparison.
     * 
     * @param other
     *            the Object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Consultant other) {
        if (this == other)
            return 0;
        return natraulOrderComparator.compare(this, other);
    }

    private Object writeReplace() {
        log.info(this.getName().toString());
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream ois) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = -5135884238966407718L;
        //private PersonalName name;
        final String lastName;
        final String firstName;
        final String middleName;

        SerializationProxy(final Consultant consultant) {
            final PersonalName name = consultant.getName();
            lastName = name.getLastName();
            firstName = name.getFirstName();
            middleName = name.getMiddleName();
        }

        private Object readResolve() {
            log.info(String.format("%s, %s %s",lastName, firstName, middleName));
            return new Consultant(new PersonalName(lastName, firstName, middleName));
        }
    }
}
