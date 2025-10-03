import { Card, CardContent, Grid, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { getActivites } from '../services/api';
import { useNavigate } from 'react-router';

const ActivityList = () => {
  const [activites, setActivites] = useState([]);
  const navigate = useNavigate();

  const fetchActivities = async () => {
    try {
      const response = await getActivites();
      setActivites(response.data);
    } catch (err) {
      console.error("Error fetching activities", err);
    }
  };

  useEffect(() => {
    fetchActivities();
  }, []);

  return (
    <Grid container spacing={2}>
      {activites.map((activity) => (
        <Grid
          item
          key={activity.id} // ✅ Add unique key here
          xs={12} sm={6} md={4} // Define responsive column widths
        >
          <Card
            sx={{ cursor: 'pointer' }}
            onClick={() => navigate(`/activities/${activity.id}`)}
          >
            <CardContent>
              <Typography variant="h6">{activity.type}</Typography>
              <Typography variant="body1">Duration: {activity.duration}</Typography>
              <Typography variant="body1">Calories: {activity.caloriesBurned}</Typography>
            </CardContent>
          </Card>
        </Grid>
      ))}
    </Grid>
  );
};

export default ActivityList;
