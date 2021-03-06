package uk.ac.bbk.cristinaborri.whoshowedapp.listAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import uk.ac.bbk.cristinaborri.whoshowedapp.R;
import uk.ac.bbk.cristinaborri.whoshowedapp.model.Attendee;

/**
 * Created by Cristina Borri
 * This class is an adapter used by the event list page to display the event
 */
public class AttendeesListItemAdapter extends ArrayAdapter<Attendee> {
    public AttendeesListItemAdapter(Context context, List<Attendee> attendees) {
        super(context, 0, attendees);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Attendee attendee = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                R.layout.list_item_attendee, parent, false
            );
        }
        // Lookup view for event name
        TextView attendeeName = convertView.findViewById(R.id.listAttendeeName);
        attendeeName.setText(attendee != null ? attendee.getName() : null);
        TextView attendeeEmail = convertView.findViewById(R.id.listAttendeeEmail);
        attendeeEmail.setText(attendee != null ? attendee.getEmail() : null);
        ImageView attendanceIndicator = convertView.findViewById(R.id.attendance_indicator);
        attendanceIndicator.setVisibility(View.INVISIBLE);
        if (attendee != null && attendee.hasAttended()) {
            attendanceIndicator.setVisibility(View.VISIBLE);
        }
        // Return the completed view to render on screen
        return convertView;
    }
}