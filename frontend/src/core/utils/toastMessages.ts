import { toast } from "react-toastify";

export const messageSuccess = (message: string) => {
    toast.success(message);
}

export const messageError = (message: string) => {
    toast.error(message);
}

export const messageInfo = (message: string) => {
    toast.info(message);
}

export const messageWarning = (message: string) => {
    toast.warning(message);
}