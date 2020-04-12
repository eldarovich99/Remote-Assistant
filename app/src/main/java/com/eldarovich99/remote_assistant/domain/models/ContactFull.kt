package com.eldarovich99.remote_assistant.domain.models

class ContactFull(id: String, name: String, description: String,
                  val category_id: String, val category_name: String): ContactBrief(id, name, description)