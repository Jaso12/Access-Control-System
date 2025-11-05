package release;

import release.requests.RequestReader;
import java.util.List;

public class SpaceRestriction extends Restriction {
    // ----------------------------------Attribute section------------------------------------------- \\
    // Although now there's virtually no way of changing this attribute in the future might be useful.
    private List<Space> restrictedSpaces;

    // ------------------------------------Method section--------------------------------------------- \\
    public SpaceRestriction(List<Space> spaces) { restrictedSpaces = spaces; }
    public SpaceRestriction(List<Space> spaces, Restriction restriction) {
        super(restriction);
        restrictedSpaces=spaces;
    }

    /* Chain of responsibility pattern: if the space where the user is willing to enter is not in the
     * subset of possible space it will decline the authorization. */
    @Override
    public boolean authorize(RequestReader request) {
        Door door= DirectoryDoors.findDoorById(request.getDoorId());
        boolean restrictedArea = restrictedSpaces.contains(door.getToSpace()) && restrictedSpaces.contains(door.getFromSpace());

        if(restrictedArea){
            return super.authorize(request);
        }
        else{
            request.addReason("Not an authorized space for " + DirectoryGroups.findUserByCredential(request.getCredential()).getName());
            return false;
        }
    }
}
