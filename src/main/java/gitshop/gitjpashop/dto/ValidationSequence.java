package gitshop.gitjpashop.dto;

import jakarta.validation.GroupSequence;

@GroupSequence({ValidationGroups.NotEmptyCheckGroup.class,
        ValidationGroups.SizeCheckGroup.class})
public interface ValidationSequence {

}
