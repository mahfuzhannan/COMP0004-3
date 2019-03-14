package uk.ac.ucl.entities;

public class Name {

    private String prefix;
    private String first;
    private String last;
    private String suffix;

    public Name() {
    }

    public Name(String prefix, String first, String last, String suffix) {
        this.prefix = prefix;
        this.first = first;
        this.last = last;
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public String toString() {
        return prefix + " " + first + " " + last + " " + suffix;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Name) {
            Name other = (Name)obj;
            return prefix.equals(other.prefix) && first.equals(other.first) && last.equals(other.last) && suffix.equals(other.suffix);
        } else {
            return false;
        }
    }
}
