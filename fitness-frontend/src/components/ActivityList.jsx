import { Card, CardContent, Grid, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react'
import { getActivites } from '../services/api';

const ActivityList = () => {
  const [activites, setActivites] = useState([]);
  const navigate = useNavigate();


  const fetchActivities = async () => {
    try{
        const response = await getActivites();
        setActivites(response.data);
    }
    catch(err){
      console.error("Error fetching activities", err);
    }
  }

  useEffect(() => {
    fetchActivities();
  }, [])
  return (
    <Grid container spacing={2}>
       {activites.map((activity) =>   (
        <Grid container spacing={ {xs:2 , md:4}} columns={{ xs: 4, sm: 8, md: 12 }} >
          <Card sx={{cursor: 'pointer'}}
          onClick={() => navigate(`/activities/${activity.id}`)}>
            <CardContent>
              <Typography variant = 'h6' >{activity.type}</Typography>
              <Typography variant = 'h6' >Duration: {activity.duration}</Typography>
              <Typography variant = 'h6' >Calories: {activity.caloriesBurned}</Typography>
            </CardContent>
          </Card>
        </Grid>
       ))}
    </Grid>
  )
}

export default ActivityList