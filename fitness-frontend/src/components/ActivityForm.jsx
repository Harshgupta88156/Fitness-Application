import { Box, Button, duration, FormControl, InputLabel, MenuItem, Select, TextField } from '@mui/material'
import React, { useState } from 'react'
import './ActivityForm.css';   // 👈 Import local CSS
import { addActivity } from '../services/api';

const ActivityForm = ({onActivityAdded}) => {
    const [activity, setActivity] = useState({
        type: 'RUNNING',duration: '', calorieBurned : '',
        additionalMetrics:{}
    });

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            await addActivity(activity);
            onActivityAdded();
            setActivity({
        type: 'RUNNING',duration: '', calorieBurned : '',
        additionalMetrics:{}
    });


        }
        catch(err){ 
            console.error("Error adding activity", err);
        }
        
    }




  return (

    


    <Box component="form" sx={{mb: 2}} onSubmit={handleSubmit} className="activity-form">
      <FormControl fullWidth sx={{mb: 2}}>
  <InputLabel id="demo-simple-select-label">Activity Type</InputLabel>
  <Select
    labelId="activity-type-label"
    id="activity-type"
    value={activity.type}
    onChange={(e) => {setActivity({...activity, type: e.target.value})}}
  >
    <MenuItem value="RUNNING">Running</MenuItem>
    <MenuItem value="WALKING">Walking</MenuItem>
<MenuItem value="CYCLING">Cycling</MenuItem>
  </Select>
</FormControl>

    

    <TextField fullWidth label="Duration" type='number' sx={{mb: 2}}
    value={activity.duration}
    onChange={(e) => {setActivity({...activity, duration: e.target.value})}}

    />

     <TextField fullWidth label="Calories Burned" type='number' sx={{mb: 2}}
    value={activity.calorieBurned}
    onChange={(e) => {setActivity({...activity, calorieBurned: e.target.value})}}

    />
    <Button type='submit' variant="contained" >Add Activity</Button>

    </Box>
  )
}

export default ActivityForm