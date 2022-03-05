package com.mytheresa.challenge.exceptions

class BadRequestException(val errorCode: ErrorCode): Exception(errorCode.msg)