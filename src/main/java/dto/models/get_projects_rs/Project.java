package dto.models.get_projects_rs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Project {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("announcement")
    @Expose
    public String announcement;
    @SerializedName("show_announcement")
    @Expose
    public Boolean showAnnouncement;
    @SerializedName("is_completed")
    @Expose
    public Boolean isCompleted;
    @SerializedName("completed_on")
    @Expose
    public Object completedOn;
    @SerializedName("suite_mode")
    @Expose
    public Integer suiteMode;
    @SerializedName("default_role_id")
    @Expose
    public Object defaultRoleId;
    @SerializedName("case_statuses_enabled")
    @Expose
    public Boolean caseStatusesEnabled;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("users")
    @Expose
    public List<Object> users;
    @SerializedName("groups")
    @Expose
    public List<Object> groups;
}
