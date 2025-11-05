package release;

import release.requests.RequestReader;
import java.util.List;

public class ActionRestriction extends Restriction {
    // ----------------------------------Attribute section------------------------------------------- \\
    // Although now there's virtually no way of changing this attribute in the future might be useful.
    private List<String> restrictedActions;

    // ------------------------------------Method section--------------------------------------------- \\
    public ActionRestriction(List<String> restrictedActions, Restriction restriction){
        super(restriction);
        this.restrictedActions=restrictedActions;
    }
    public ActionRestriction(List<String> restrictedActions){
        this.restrictedActions=restrictedActions;
    }

    /* Chain of responsibility pattern: if the action that the user is willing to perform is not in the
    *  list of possible actions it will decline the authorization. */
    @Override
    public boolean authorize(RequestReader request) {
        if(restrictedActions.contains(request.getAction())){
            return super.authorize(request);
        }
        else{
            request.addReason("Not an authorized action for " + DirectoryGroups.findUserByCredential(request.getCredential()).getName());
            return false;
        }
    }
}
