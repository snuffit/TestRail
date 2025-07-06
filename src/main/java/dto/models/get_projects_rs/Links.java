package dto.models.get_projects_rs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Links {

    @SerializedName("next")
    @Expose
    public Object next;
    @SerializedName("prev")
    @Expose
    public Object prev;
}
