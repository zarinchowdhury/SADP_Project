import com.coachingcenter.Student1;

// ========================================================
// ADAPTER PATTERN
// ========================================================
class LegacyStudent {
public String fullName;
public int years;
}


class LegacyStudentAdapter extends Student1 {
public LegacyStudentAdapter(LegacyStudent legacy){
    super(legacy.fullName, legacy.years, "N/A", "N/A");
}
}