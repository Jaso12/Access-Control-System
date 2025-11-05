package release;

import release.requests.RequestReader;

/* We use the Chain of Responsibility pattern to connect several restrictions that must meet in order to
*  get the authorization needed. This isn't a typical implementation as "Restriction" isn't an abstract
*  class. This was a design decision so you can instantiate a regular empty restriction that gives by
*  default full access, very useful for Admin privileges. Also, the order doesn't mean much as with only
*  one restriction fail it denies the authorization. This pattern proves to be useful also to give custom
*  failing reasons to RequestReader. */
public class Restriction {
    // ----------------------------------Attribute section------------------------------------------- \\
    protected Restriction nextRestriction = null;

    // ------------------------------------Method section--------------------------------------------- \\
    public Restriction() { /* ... */ }
    public Restriction(Restriction restriction) { this.nextRestriction=restriction; }

    public void setRestriction(Restriction restriction) { nextRestriction = restriction; }
    public Restriction getRestriction() { return nextRestriction; }

    /* Chains the restrictions until it returns false (with a reason) or true if authorized. */
    public boolean authorize(RequestReader request){
        if (nextRestriction == null) {
            return true;
        }
        else {
            return nextRestriction.authorize(request);
        }
    }
}


