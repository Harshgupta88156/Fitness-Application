import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./authSlice"; // <-- import your slice reducer
export const store = configureStore({
    reducer:{
        auth: authReducer,
    },
});