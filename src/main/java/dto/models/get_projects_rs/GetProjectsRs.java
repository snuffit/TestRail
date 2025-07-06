package dto.models.get_projects_rs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class GetProjectsRs {

    @SerializedName("offset")
    @Expose
    public Integer offset;
    @SerializedName("limit")
    @Expose
    public Integer limit;
    @SerializedName("size")
    @Expose
    public Integer size;
    @SerializedName("_links")
    @Expose
    public Links links;
    @SerializedName("projects")
    @Expose
    public List<Project> projects;
}
